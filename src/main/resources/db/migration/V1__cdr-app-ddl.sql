CREATE TABLE IF NOT EXISTS "charge_detail_records"
(
    "id"            SERIAL PRIMARY KEY,
    "car_id"        VARCHAR(255) NOT NULL,
    "start_time"    TIMESTAMP NOT NULL,
    "end_time"      TIMESTAMP NOT NULL,
    "cost"          NUMERIC
);