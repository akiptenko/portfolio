import pandas as pd
import psycopg2
from sqlalchemy import create_engine
import requests

# URL файла CSV на облаке Mail.ru
#csv_url = 'https://cloud.mail.ru/public/L1xB/nvgHGYJz5'
#csv_url = 'https://cloclo60.cloud.mail.ru/public/Ni9fEFuDXV7DFSmBjQ8/g/no/L1xB/nvgHGYJz5'
csv_url = 'https://support.staffbase.com/hc/en-us/article_attachments/360009197011/username-password-recovery-code.csv'

# Локальный путь для сохранения скачанного файла
local_csv_path = 'downloaded_file.csv'

# Скачивание файла
response = requests.get(csv_url)
if response.status_code == 200:
    with open(local_csv_path, 'wb') as file:
        file.write(response.content)
    print(f"Файл успешно скачан и сохранен как {local_csv_path}")
else:
    print(f"Ошибка при скачивании файла: {response.status_code}")
    exit(1)

# Определение разделителя
separator = ';'

# Загрузка CSV файла в DataFrame
try:
    df = pd.read_csv(local_csv_path, sep=separator, on_bad_lines='skip')
    print(f"Файл успешно прочитан: {local_csv_path}")
except pd.errors.ParserError as e:
    print(f"Ошибка парсинга CSV файла: {e}")
    exit(1)

# Преобразование колонки TIME


# Переименование колонок для соответствия типам данных
#df.columns = ['ticker', 'per', 'date', 'time', 'open', 'high', 'low', 'close', 'vol']

# Преобразование типов данных

# Подключение к базе данных PostgreSQL
db_name = "mydatabase"
db_user = "akiptenko"
db_password = "Germanoid123468"
db_host = "db"
db_port = "5432"

# Создание SQLAlchemy engine
engine = create_engine(f'postgresql://{db_user}:{db_password}@{db_host}:{db_port}/{db_name}')


# Загрузка данных в таблицу
table_name = "stock_data"
df.to_sql(table_name, engine, if_exists='replace', index=False)

print("Данные успешно загружены в таблицу PostgreSQL")

