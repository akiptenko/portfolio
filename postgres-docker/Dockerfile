# Используем образ Python
FROM python:3.9-slim

# Устанавливаем необходимые пакеты
RUN pip install pandas sqlalchemy psycopg2-binary

RUN pip install requests

# Копируем скрипт загрузки данных в контейнер
COPY data_loader.py /app/data_loader.py

# Устанавливаем рабочую директорию
WORKDIR /app

# Запускаем скрипт при старте контейнера
CMD ["python", "data_loader.py"]

