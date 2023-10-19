CREATE TABLE product
(
    id character varying(40) NOT NULL,
    name character varying(100) NOT NULL,
    price numeric DEFAULT 0,
    CONSTRAINT product_pkey PRIMARY KEY (id)
);
