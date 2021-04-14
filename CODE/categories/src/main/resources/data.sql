INSERT INTO category(id,name)
VALUES(100, 'Mobiles');

INSERT INTO category(id,name)
VALUES(200, 'Bikes');

INSERT INTO item(id, title, brand, description, price, sub_category, user_id, date, time, pincode, status, category_id)
VALUES(300, 'iphonex', 'apple', '1 year old mobile', 40000, 'mobile phone', 101, '2021-04-09', '20:30:14', 560098, true, 100);

INSERT INTO item(id, title, brand, description, price, sub_category, user_id, date, time, pincode, status, category_id)
VALUES(301, 'n8', 'nokia', '8 year old mobile', 10000, 'mobile phone', 101, '2021-04-12', '21:30:14', 560098, true, 100);

INSERT INTO item(id, title, brand, description, price, sub_category, user_id, date, time, pincode, status, category_id)
VALUES(302, 'splendor', 'hero', '1000 year old bike', 4000000, 'motorcycle', 101, '2021-04-11', '17:30:14', 560070, true, 200);

INSERT INTO item(id, title, brand, description, price, sub_category, user_id, date, time, pincode, status, category_id)
VALUES(303, 'iphonex', 'apple', '1 year old mobile', 35000, 'mobile phone', 102, '2021-04-09', '21:30:14', 560070, true, 100);

INSERT INTO item(id, title, brand, description, price, sub_category, user_id, date, time, pincode, status, category_id)
VALUES(304, 'pulsar', 'bajaj', '1 year old bike', 45000, 'motorcycle', 102, '2021-04-10', '22:30:14', 560098, true, 200);



