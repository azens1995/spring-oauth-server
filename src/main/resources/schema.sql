CREATE TABLE oauth_access_token (
                                    token_id varchar(255) NOT NULL,
                                    token bytea,
                                    authentication_id varchar(255) DEFAULT NULL,
                                    user_name varchar(255) DEFAULT NULL,
                                    client_id varchar(255) DEFAULT NULL,
                                    authentication bytea,
                                    refresh_token varchar(255) DEFAULT NULL,
                                    PRIMARY KEY (token_id));

CREATE TABLE oauth_refresh_token (
                                     token_id varchar(255) NOT NULL,
                                     token bytea,
                                     authentication bytea,
                                     PRIMARY KEY (token_id));
