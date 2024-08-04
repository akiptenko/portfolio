-- Создание тестовой таблицы
CREATE TABLE stock_data (
    ticker VARCHAR(10),
    per CHAR(1),
    date DATE,
    time TIME,
    open NUMERIC(10, 2),
    high NUMERIC(10, 2),
    low NUMERIC(10, 2),
    close NUMERIC(10, 2),
    vol BIGINT
);
