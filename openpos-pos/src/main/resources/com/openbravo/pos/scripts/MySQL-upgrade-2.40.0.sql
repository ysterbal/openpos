
CREATE TABLE IF NOT EXISTS `MONTHLY_REPORTING` (
  `MONTHLY_REPORTING_ID` INT NOT NULL AUTO_INCREMENT ,
  `MONTH` INT NOT NULL ,
  `YEAR` INT NOT NULL ,
  `STATUS` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`MONTHLY_REPORTING_ID`))
  ENGINE = INNODB;

DELETE FROM SHAREDTICKETS;

UPDATE APPLICATIONS SET NAME = $APP_NAME{}, VERSION = $APP_VERSION{} WHERE ID = $APP_ID{};