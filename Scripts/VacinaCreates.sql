CREATE TABLE `exemplos`.`pessoa` (
  `ID` INT NOT NULL,
  `NOME` VARCHAR(225) NOT NULL,
  `DT_NASCIMENTO` DATE NOT NULL,
  `SEXO` VARCHAR(45) NOT NULL,
  `CPF` VARCHAR(13) NOT NULL,
  `TIPO_PESSOA` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`));
  

ALTER TABLE `exemplosdd`.`pessoa` 
ADD COLUMN `TIPO_PESSOA` INT NOT NULL AFTER `CPF`;


  
  
  CREATE TABLE `exemplosdd`.`vacina` (
  `ID` INT NOT NULL,
  `PAIS_ORIGEM` VARCHAR(45) NOT NULL,
  `ESTAGIO_PESQUISA` VARCHAR(45) NOT NULL,
  `DT_INICIO_PESQUISA` VARCHAR(45) NOT NULL,
  `NOME_RESPONSAVEL` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`));

  CREATE TABLE `exemplos`.`vacina` (
  `ID` INT NOT NULL,
  `PAIS_ORIGEM` VARCHAR(45) NOT NULL,
  `ESTAGIO_PESQUISA` VARCHAR(45) NOT NULL,
  `DT_INICIO_PESQUISA` DATE NOT NULL,
  PRIMARY KEY (`ID`));

  
  -- já coloquei a FK de pessoa em Vacina