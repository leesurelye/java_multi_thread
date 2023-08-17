CREATE
EXTERNAL CATALOG IF NOT EXISTS POSTGRESQL 
AS PG_DEMO
WITH (
    'password':'1qaz!QAZ',
    'database':'demo',
    'port':'5432',
    'host':'10.15.255.1',
    'username':'demo'
);