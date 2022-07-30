DELIMITER $$

CREATE OR REPLACE PROCEDURE findTodoByText (IN term VARCHAR(255))
BEGIN
    SELECT * FROM todo WHERE text LIKE concat('%', term, '%');
END; $$

DELIMITER ;