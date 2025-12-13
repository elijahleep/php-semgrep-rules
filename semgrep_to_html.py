# semgrep_to_html.py
import json, sys, html, datetime, os

def main(inp, outp):
    with open(inp, "r", encoding="utf-8") as f:
        data = json.load(f)

    results = data.get("results", [])
    meta = data.get("meta", {})
    started = meta.get("start_time") or meta.get("timestamp") or ""
    run_info = f"Rules run: {meta.get('rules', {}).get('nr_rules', 'N/A')}, Findings: {len(results)}"

    # Группируем по файлам
    by_file = {}
    for r in results:
        path = r.get("path", "UNKNOWN")
        by_file.setdefault(path, []).append(r)

    # HTML
    def esc(s): return html.escape(str(s), quote=True)

    rows = []
    for path, items in sorted(by_file.items()):
        rows.append(f"<h2>{esc(path)} <small>({len(items)} findings)</small></h2>")
        rows.append("<table>")
        rows.append("<thead><tr><th>Rule</th><th>Severity</th><th>Message</th><th>Line</th><th>Column</th></tr></thead><tbody>")
        for it in items:
            check_id = it.get("check_id", "")
            sev = it.get("extra", {}).get("severity", "")
            msg = it.get("extra", {}).get("message", "")
            start = it.get("start", {}) or {}
            line = start.get("line", "")
            col = start.get("col", "")
            rows.append(
                "<tr>"
                f"<td>{esc(check_id)}</td>"
                f"<td>{esc(sev)}</td>"
                f"<td>{esc(msg)}</td>"
                f"<td>{esc(line)}</td>"
                f"<td>{esc(col)}</td>"
                "</tr>"
            )
        rows.append("</tbody></table>")

    html_doc = f"""<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Semgrep Report</title>
<style>
body{{font-family:system-ui,-apple-system,Segoe UI,Roboto,Arial,sans-serif; margin:20px;}}
h1{{margin-bottom:0}}
summary{{cursor:pointer}}
table{{border-collapse:collapse; width:100%; margin:8px 0 24px}}
th,td{{border:1px solid #ddd; padding:8px; vertical-align:top}}
th{{background:#f6f6f6; text-align:left}}
small{{color:#666}}
.badge{{display:inline-block;padding:2px 6px;border-radius:6px;background:#eee;margin-left:6px;font-size:12px}}
</style>
</head>
<body>
<h1>Semgrep Report</h1>
<p><small>Generated: {esc(datetime.datetime.now().isoformat(timespec='seconds'))}</small></p>
<p>{esc(run_info)}</p>
{''.join(rows) if rows else '<p>No findings.</p>'}
</body></html>"""

    with open(outp, "w", encoding="utf-8") as f:
        f.write(html_doc)

if __name__ == "__main__":
    if len(sys.argv) < 3:
        print("Usage: python semgrep_to_html.py <semgrep.json> <semgrep.html>")
        sys.exit(1)
    main(sys.argv[1], sys.argv[2])