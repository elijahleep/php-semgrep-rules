# Semgrep Scan

Проект содержит набор правил Semgrep, результаты сканирования и скрипт для преобразования отчётов в HTML-формат для удобного просмотра.

## Структура проекта

.
├── rules/              # правила Semgrep в формате YAML
├── semgrep_scan/       # результаты запуска Semgrep стандартным набором
├── my_scan/            # результаты запуска Semgrep разработанным набором
└── semgrep_to_html.py  # Python-скрипт для конвертации отчёта Semgrep в HTML

## Использование

### Запуск Semgrep с кастомными правилами

semgrep scan --config=rules ./path/to/code --json --output semgrep_scan/report.json

### Преобразование отчёта в HTML

python semgrep_to_html.py semgrep_scan/report.json my_scan/report.html
