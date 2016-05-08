INSERT IGNORE INTO items (id, item_name) VALUES (1,'item1');
INSERT IGNORE INTO items (id, item_name) VALUES (2,'item2');
INSERT IGNORE INTO item_attr(attr_id, itemId, attr_value) VALUES (1,1,'attribute value1');
INSERT IGNORE INTO item_attr(attr_id, itemId, attr_value) VALUES (1,2,'attribute value2')