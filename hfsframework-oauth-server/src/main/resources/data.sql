INSERT INTO oauth_client_details(client_id, client_secret, scope, authorized_grant_types, authorities, access_token_validity, refresh_token_validity)
VALUES ('hfsClient', '$2a$10$k/Ijmj2O8mvOT2HsDvU8LOYaVSl/.8hmcExQSOOQzlIH1LpSLs9pG', 'read,write,trust', 'password,refresh_token,authorization_code,client_credentials,implicit', 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 1800, 2592000);
