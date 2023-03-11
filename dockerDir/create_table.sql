CREATE TABLE informacion_generada (
    hora character varying NOT NULL,
    genero character varying,
    ciudad character varying,
    clima character varying,
    canciones character varying
);

CREATE TABLE users (
    users character varying,
    password character varying,
    token character varying
);

INSERT INTO public.users(
	users, password, token)
	VALUES ('edwin', '20254820b2c8ea8a3ff8705e46509a2bf4fd9a6b8243e64b7dabe118f8ff7639', '');