DELETE FROM t_inventory
WHERE sku_code IN ('iphone_15','pixel_8','galaxy_24','oneplus_12');

INSERT INTO t_inventory(quantity, sku_code)
VALUES 	(100, 'iphone_15'),
		(100, 'pixel_8'),
		(100, 'galaxy_24'),
		(100, 'oneplus_12');