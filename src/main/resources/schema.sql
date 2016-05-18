DELETE FROM item_attr_dep;
DELETE FROM item_attr;
DELETE FROM items;
INSERT IGNORE INTO items (id, item_name) VALUES (1,'item1');
INSERT IGNORE INTO items (id, item_name) VALUES (2,'item2');
INSERT IGNORE INTO item_attr(attr_id, itemId, attr_value) VALUES (1,1,'attribute value1');
INSERT IGNORE INTO item_attr(attr_id, itemId, attr_value) VALUES (2,2,'attribute value2');
insert IGNORE INTO item_attr_dep(attr_dep_id, attrId, dep_value, attributes_attr_id)
VALUES(1,1,'value',1);