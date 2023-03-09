CREATE SCHEMA 'EXEMPLOS';

CREATE TABLE `exemplos`.`endereco` (
  `ID` INT NOT NULL,
  `RUA` VARCHAR(255) NOT NULL,
  `CEP` VARCHAR(8) NOT NULL,
  `BAIRRO` VARCHAR(255) NOT NULL,
  `CIDADE` VARCHAR(255) NOT NULL,
  `ESTADO` VARCHAR(2) GENERATED ALWAYS AS () VIRTUAL,
  `NUMERO` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC) VISIBLE);
  
 
  CREATE TABLE `exemplos`.`telefone` (
  `ID` INT NOT NULL,
  `DDD` VARCHAR(3) NOT NULL,
  `NUMERO` VARCHAR(9) NOT NULL,
  `ATIVO` TINYINT NOT NULL,
  `MOVEL` TINYINT NOT NULL,
  `IDCLIENTE` INT NULL,
  PRIMARY KEY (`ID`));

  
  CREATE TABLE `exemplos`.`cliente` (
  `ID` INT NOT NULL,
  `NOME` VARCHAR(255) NOT NULL,
  `CPF` VARCHAR(11) NOT NULL,
  `ATIVO` TINYINT NOT NULL,
  `ID_ENDERECO` INT NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC) VISIBLE,
  UNIQUE INDEX `CPF_UNIQUE` (`CPF` ASC) VISIBLE,
  INDEX `ID_ENDERECO_idx` (`ID_ENDERECO` ASC) VISIBLE,
  CONSTRAINT `ID_ENDERECO`
    FOREIGN KEY (`ID_ENDERECO`)
    REFERENCES `exemplos`.`endereco` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

