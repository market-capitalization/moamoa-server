# import mysql.connector
import os
from dotenv import load_dotenv
import pandas as pd
import datetime
from sqlalchemy import create_engine

load_dotenv()

MYSQL_USER = os.getenv("MYSQL_USER")
MYSQL_PW = os.getenv("MYSQL_PW")
TABLE_NAME = "stock"

engine = create_engine(f"mysql+pymysql://{MYSQL_USER}:{MYSQL_PW}@127.0.0.1:3306/market-capital-db?charset=utf8")
connection = engine.connect()

df = pd.read_csv("./stock_data/csv-data/stock.csv", encoding="utf-8")

df.rename(columns = {'키워드':'keyword','주식명':'stock_name', '연령대':'age', '성별': 'gender'},inplace=True)
df['created_date'] = datetime.datetime.now().strftime("%Y-%m-%dT%H:%M:%S.%f")
df['last_modified_date'] = df['created_date']
df['status'] = "ACTIVE"

df.loc[df['gender'] == 'F', 'gender'] = 'FEMALE'
df.loc[df['gender'] == 'M', 'gender'] = 'MALE'

df = df[['created_date', 'last_modified_date', 'status', 'age', 'gender', 'keyword', 'stock_name']]

print(df.head())

df.to_sql(name=TABLE_NAME,
          con=connection,
          if_exists='append', # or 'replace'
          index=False) 

