import datetime
import logging
import os
from flask import Flask, render_template, request, Response
import sqlalchemy
# Remember - storing secrets in plaintext is potentially unsafe. Consider using
# something like https://cloud.google.com/kms/ to help keep secrets secret.
db_user = "postgres"
db_pass = "csb312"
db_name = "database"
cloud_sql_connection_name = "adroit-nimbus-272020:us-central1:database"

app = Flask(__name__)

logger = logging.getLogger()

# The SQLAlchemy engine will help manage interactions, including automatically
# managing a pool of connections to your database
db = sqlalchemy.create_engine(
    # Equivalent URL:
    # postgres+pg8000://<db_user>:<db_pass>@/<db_name>?unix_sock=/cloudsql/<cloud_sql_instance_name>/.s.PGSQL.5432
    sqlalchemy.engine.url.URL(
        drivername='postgres+pg8000',
        username=db_user,
        password=db_pass,
        database=db_name,
        query={'unix_sock': '/cloudsql/{}/.s.PGSQL.5432'.format(cloud_sql_connection_name)
        }
    ),
    # ... Specify additional properties here.
    # ...
)


@app.route('/', methods=['GET'])
def index():
    with db.connect() as conn:
        # Execute the query and fetch all results
        query = conn.execute("SELECT * FROM company").fetchall()

if __name__ == '__main__':
    app.run(host='127.0.0.1', port=8080, debug=True)