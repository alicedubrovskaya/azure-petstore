insert into public.category(id, name) values(1, 'Dog');
insert into public.category(id, name) values(2, 'Cat');

insert into public.tag(id, name) values(1, 'doggie');
insert into public.tag(id, name) values(2, 'large');
insert into public.tag(id, name) values(3, 'small');
insert into public.tag(id, name) values(4, 'kittie');

INSERT INTO public.pet(id, name, photourl, status, category_id) VALUES(1, 'Afador', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-breeds/afador.jpg?raw=true', 0, 1);
INSERT INTO public.pet(id, name, photourl, status, category_id) VALUES(2, 'American Bulldog', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-breeds/american-bulldog.jpg?raw=true', 0, 1);
INSERT INTO public.pet(id, name, photourl, status, category_id) VALUES(3, 'Australian Retriever', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-breeds/australian-retriever.jpg?raw=true', 0, 1);
INSERT INTO public.pet(id, name, photourl, status, category_id) VALUES(4, 'Australian Shepherd', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-breeds/australian-shepherd.jpg?raw=true', 0, 1);
INSERT INTO public.pet(id, name, photourl, status, category_id) VALUES(5, 'Basset Hound', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-breeds/basset-hound.jpg?raw=true', 0, 1);
INSERT INTO public.pet(id, name, photourl, status, category_id) VALUES(6, 'Beagle', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/dog-breeds/beagle.jpg?raw=true', 0, 1);

INSERT INTO public.pet(id, name, photourl, status, category_id) VALUES(21, 'Abyssinian', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/cat-breeds/abyssinian.jpg?raw=true', 0, 2);
INSERT INTO public.pet(id, name, photourl, status, category_id) VALUES(22, 'American Bobtail', 'https://raw.githubusercontent.com/chtrembl/staticcontent/master/cat-breeds/american-bobtail.jpg?raw=true', 0, 2);

INSERT INTO public.pettags(pet_id, tag_id) VALUES(1, 1);
INSERT INTO public.pettags(pet_id, tag_id) VALUES(1, 2);

INSERT INTO public.pettags(pet_id, tag_id) VALUES(2, 1);
INSERT INTO public.pettags(pet_id, tag_id) VALUES(2, 2);

INSERT INTO public.pettags(pet_id, tag_id) VALUES(3, 1);
INSERT INTO public.pettags(pet_id, tag_id) VALUES(3, 2);

INSERT INTO public.pettags(pet_id, tag_id) VALUES(4, 1);
INSERT INTO public.pettags(pet_id, tag_id) VALUES(4, 2);

INSERT INTO public.pettags(pet_id, tag_id) VALUES(5, 1);
INSERT INTO public.pettags(pet_id, tag_id) VALUES(5, 3);

INSERT INTO public.pettags(pet_id, tag_id) VALUES(6, 1);
INSERT INTO public.pettags(pet_id, tag_id) VALUES(6, 3);

INSERT INTO public.pettags(pet_id, tag_id) VALUES(21, 4);
INSERT INTO public.pettags(pet_id, tag_id) VALUES(21, 3);

INSERT INTO public.pettags(pet_id, tag_id) VALUES(22, 4);
INSERT INTO public.pettags(pet_id, tag_id) VALUES(22, 3);