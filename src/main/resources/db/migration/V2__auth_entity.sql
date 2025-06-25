CREATE TABLE IF NOT EXISTS users_schema.t_roles(

    id SERIAL PRIMARY KEY;
    name VARCHAR(50) UNIQUE NOT NULL



);


CREATE TABLE IF NOT EXISTS users_schema.t_credentials(
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(250) NOT NULL,
    c_user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (c_user_id) REFERENCES users_schema.t_users(id),
    FOREIGN KEY (c_role_id) REFERENCES users_schema.t_users(id)



)