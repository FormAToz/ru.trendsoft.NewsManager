CREATE TABLE news_items
(
    id bigserial NOT NULL,
    content character varying(500),
    date timestamp with time zone,
    name character varying(500),
    category_id bigint NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id bigserial NOT NULL,
    name character varying(255),
    PRIMARY KEY (id)
);