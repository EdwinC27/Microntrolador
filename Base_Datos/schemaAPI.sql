CREATE TABLE public.informacion_generada (
    hora character varying NOT NULL,
    genero character varying,
    ciudad character varying,
    clima character varying,
    canciones character varying
);



ALTER TABLE ONLY public.informacion_generada
    ADD CONSTRAINT informacion_generada_pkey PRIMARY KEY (hora);
