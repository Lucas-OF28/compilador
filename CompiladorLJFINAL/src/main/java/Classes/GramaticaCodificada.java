package Classes;

import java.util.ArrayList;

public class GramaticaCodificada {

  ArrayList<Regra> regras = new ArrayList<Regra>();
  Dicionario dicionario = new Dicionario();
  public GramaticaCodificada() {

    regras.add(0, null);

    regras.add(1,new Regra(
        dicionario.retornaTokenDicionarioCodigo(2),
        dicionario.retornaTokenDicionarioCodigo(10),
        dicionario.retornaTokenDicionarioCodigo(36), 
        dicionario.retornaTokenDicionarioCodigo(49),
        dicionario.retornaTokenDicionarioCodigo(50), 
        dicionario.retornaTokenDicionarioCodigo(51),
        dicionario.retornaTokenDicionarioCodigo(35)));

    regras.add(2,new Regra(
        dicionario.retornaTokenDicionarioCodigo(7), 
        dicionario.retornaTokenDicionarioCodigo(100),
        dicionario.retornaTokenDicionarioCodigo(52), 
        dicionario.retornaTokenDicionarioCodigo(38),
        dicionario.retornaTokenDicionarioCodigo(53), 
        dicionario.retornaTokenDicionarioCodigo(37),
        dicionario.retornaTokenDicionarioCodigo(54)));

    regras.add(3, new Regra(
        dicionario.retornaTokenDicionarioCodigo(15)));

    regras.add(4, new Regra(
        dicionario.retornaTokenDicionarioCodigo(15)));

    regras.add(5, new Regra(
        dicionario.retornaTokenDicionarioCodigo(40), 
        dicionario.retornaTokenDicionarioCodigo(7),
        dicionario.retornaTokenDicionarioCodigo(100), 
        dicionario.retornaTokenDicionarioCodigo(52)));

    regras.add(6, new Regra(
        dicionario.retornaTokenDicionarioCodigo(12), 
        dicionario.retornaTokenDicionarioCodigo(101)));

    regras.add(7, new Regra(
        dicionario.retornaTokenDicionarioCodigo(17), 
        dicionario.retornaTokenDicionarioCodigo(101)));

    regras.add(8, new Regra(
        dicionario.retornaTokenDicionarioCodigo(3), 
        dicionario.retornaTokenDicionarioCodigo(101)));

    regras.add(9, new Regra(
        dicionario.retornaTokenDicionarioCodigo(23), 
        dicionario.retornaTokenDicionarioCodigo(101)));

    regras.add(10, new Regra(
        dicionario.retornaTokenDicionarioCodigo(15)));

    regras.add(11, new Regra(
        dicionario.retornaTokenDicionarioCodigo(55), 
        dicionario.retornaTokenDicionarioCodigo(38),
        dicionario.retornaTokenDicionarioCodigo(53),
        dicionario.retornaTokenDicionarioCodigo(37), 
        dicionario.retornaTokenDicionarioCodigo(54)));

    regras.add(12, new Regra(
        dicionario.retornaTokenDicionarioCodigo(7), 
        dicionario.retornaTokenDicionarioCodigo(100),
        dicionario.retornaTokenDicionarioCodigo(52)));

    regras.add(13, new Regra(
        dicionario.retornaTokenDicionarioCodigo(56), 
        dicionario.retornaTokenDicionarioCodigo(7),
        dicionario.retornaTokenDicionarioCodigo(100),
        dicionario.retornaTokenDicionarioCodigo(57), 
        dicionario.retornaTokenDicionarioCodigo(36),
        dicionario.retornaTokenDicionarioCodigo(49),
        dicionario.retornaTokenDicionarioCodigo(50), 
        dicionario.retornaTokenDicionarioCodigo(51),
        dicionario.retornaTokenDicionarioCodigo(4),
        dicionario.retornaTokenDicionarioCodigo(43), 
        dicionario.retornaTokenDicionarioCodigo(58),
        dicionario.retornaTokenDicionarioCodigo(42),
        dicionario.retornaTokenDicionarioCodigo(35), 
        dicionario.retornaTokenDicionarioCodigo(50)));

    regras.add(14, new Regra(
        dicionario.retornaTokenDicionarioCodigo(12)));

    regras.add(15, new Regra(
        dicionario.retornaTokenDicionarioCodigo(2)));

    regras.add(16, new Regra(
        dicionario.retornaTokenDicionarioCodigo(23)));

    regras.add(17, new Regra(
        dicionario.retornaTokenDicionarioCodigo(17)));

    regras.add(18, new Regra(
        dicionario.retornaTokenDicionarioCodigo(3)));

    regras.add(19, new Regra(
        dicionario.retornaTokenDicionarioCodigo(15)));

    regras.add(20, new Regra(
        dicionario.retornaTokenDicionarioCodigo(5)));

    regras.add(21, new Regra(
        dicionario.retornaTokenDicionarioCodigo(6)));

    regras.add(22, new Regra(
        dicionario.retornaTokenDicionarioCodigo(7), 
        dicionario.retornaTokenDicionarioCodigo(104)));

    regras.add(23, new Regra(
        dicionario.retornaTokenDicionarioCodigo(8)));

    regras.add(24, new Regra(
        dicionario.retornaTokenDicionarioCodigo(9)));

    regras.add(25, new Regra(
        dicionario.retornaTokenDicionarioCodigo(15)));

    regras.add(26, new Regra(
        dicionario.retornaTokenDicionarioCodigo(15)));

    regras.add(27, new Regra(
        dicionario.retornaTokenDicionarioCodigo(43), 
        dicionario.retornaTokenDicionarioCodigo(59),
        dicionario.retornaTokenDicionarioCodigo(42)));

    regras.add(28, new Regra(
        dicionario.retornaTokenDicionarioCodigo(53), 
        dicionario.retornaTokenDicionarioCodigo(60)));

    regras.add(29, new Regra(
        dicionario.retornaTokenDicionarioCodigo(37), 
        dicionario.retornaTokenDicionarioCodigo(53),
        dicionario.retornaTokenDicionarioCodigo(60)));

    regras.add(30, new Regra(
        dicionario.retornaTokenDicionarioCodigo(15)));

    regras.add(31, new Regra(
        dicionario.retornaTokenDicionarioCodigo(13), 
        dicionario.retornaTokenDicionarioCodigo(61),
        dicionario.retornaTokenDicionarioCodigo(37), 
        dicionario.retornaTokenDicionarioCodigo(62),
        dicionario.retornaTokenDicionarioCodigo(18)));

    regras.add(32, new Regra(
        dicionario.retornaTokenDicionarioCodigo(15)));

    regras.add(33, new Regra(
        dicionario.retornaTokenDicionarioCodigo(61), 
        dicionario.retornaTokenDicionarioCodigo(37),
        dicionario.retornaTokenDicionarioCodigo(62)));

    regras.add(34, new Regra(
        dicionario.retornaTokenDicionarioCodigo(7), 
        dicionario.retornaTokenDicionarioCodigo(104),
        dicionario.retornaTokenDicionarioCodigo(29), 
        dicionario.retornaTokenDicionarioCodigo(63),
        dicionario.retornaTokenDicionarioCodigo(103)));

    regras.add(35, new Regra(
        dicionario.retornaTokenDicionarioCodigo(9), 
        dicionario.retornaTokenDicionarioCodigo(29),
        dicionario.retornaTokenDicionarioCodigo(63),
        dicionario.retornaTokenDicionarioCodigo(103)));

    regras.add(36, new Regra(
        dicionario.retornaTokenDicionarioCodigo(8), 
        dicionario.retornaTokenDicionarioCodigo(29),
        dicionario.retornaTokenDicionarioCodigo(63), 
        dicionario.retornaTokenDicionarioCodigo(103)));

    regras.add(37, new Regra(
        dicionario.retornaTokenDicionarioCodigo(63), 
        dicionario.retornaTokenDicionarioCodigo(29),
        dicionario.retornaTokenDicionarioCodigo(63), 
        dicionario.retornaTokenDicionarioCodigo(103)));

    regras.add(38, new Regra(
        dicionario.retornaTokenDicionarioCodigo(15)));

    regras.add(39, new Regra(
        dicionario.retornaTokenDicionarioCodigo(24), 
        dicionario.retornaTokenDicionarioCodigo(7),
        dicionario.retornaTokenDicionarioCodigo(104), 
        dicionario.retornaTokenDicionarioCodigo(64)));

    regras.add(40, new Regra(
        dicionario.retornaTokenDicionarioCodigo(15)));

    regras.add(41, new Regra(
        dicionario.retornaTokenDicionarioCodigo(43), 
        dicionario.retornaTokenDicionarioCodigo(65),
        dicionario.retornaTokenDicionarioCodigo(66), 
        dicionario.retornaTokenDicionarioCodigo(42)));

    regras.add(42, new Regra(
        dicionario.retornaTokenDicionarioCodigo(15)));

    regras.add(43, new Regra(
        dicionario.retornaTokenDicionarioCodigo(40), 
        dicionario.retornaTokenDicionarioCodigo(65),
        dicionario.retornaTokenDicionarioCodigo(66)));

    regras.add(44, new Regra(
        dicionario.retornaTokenDicionarioCodigo(5)));

    regras.add(45, new Regra(
        dicionario.retornaTokenDicionarioCodigo(9)));

    regras.add(46, new Regra(
        dicionario.retornaTokenDicionarioCodigo(6)));

    regras.add(47, new Regra(
        dicionario.retornaTokenDicionarioCodigo(8)));

    regras.add(48, new Regra(
        dicionario.retornaTokenDicionarioCodigo(7), 
        dicionario.retornaTokenDicionarioCodigo(104)));

    regras.add(49, new Regra(
        dicionario.retornaTokenDicionarioCodigo(14), 
        dicionario.retornaTokenDicionarioCodigo(43),
        dicionario.retornaTokenDicionarioCodigo(7), 
        dicionario.retornaTokenDicionarioCodigo(104),
        dicionario.retornaTokenDicionarioCodigo(67), 
        dicionario.retornaTokenDicionarioCodigo(42),
        dicionario.retornaTokenDicionarioCodigo(36),
        dicionario.retornaTokenDicionarioCodigo(61), 
        dicionario.retornaTokenDicionarioCodigo(37),
        dicionario.retornaTokenDicionarioCodigo(62),
        dicionario.retornaTokenDicionarioCodigo(35), 
        dicionario.retornaTokenDicionarioCodigo(68)));

    regras.add(50, new Regra(
        dicionario.retornaTokenDicionarioCodigo(19), 
        dicionario.retornaTokenDicionarioCodigo(36),
        dicionario.retornaTokenDicionarioCodigo(61), 
        dicionario.retornaTokenDicionarioCodigo(37),
        dicionario.retornaTokenDicionarioCodigo(62),
        dicionario.retornaTokenDicionarioCodigo(35)));

    regras.add(51, new Regra(
        dicionario.retornaTokenDicionarioCodigo(15)));

    regras.add(52, new Regra(
        dicionario.retornaTokenDicionarioCodigo(1), 
        dicionario.retornaTokenDicionarioCodigo(43),
        dicionario.retornaTokenDicionarioCodigo(7), 
        dicionario.retornaTokenDicionarioCodigo(104),
        dicionario.retornaTokenDicionarioCodigo(67), 
        dicionario.retornaTokenDicionarioCodigo(42),
        dicionario.retornaTokenDicionarioCodigo(36), 
        dicionario.retornaTokenDicionarioCodigo(61),
        dicionario.retornaTokenDicionarioCodigo(37),
        dicionario.retornaTokenDicionarioCodigo(62), 
        dicionario.retornaTokenDicionarioCodigo(35)));

    regras.add(53, new Regra(
        dicionario.retornaTokenDicionarioCodigo(28), 
        dicionario.retornaTokenDicionarioCodigo(69)));

    regras.add(54, new Regra(
        dicionario.retornaTokenDicionarioCodigo(45), 
        dicionario.retornaTokenDicionarioCodigo(69)));

    regras.add(55, new Regra(
        dicionario.retornaTokenDicionarioCodigo(27), 
        dicionario.retornaTokenDicionarioCodigo(69)));

    regras.add(56, new Regra(
        dicionario.retornaTokenDicionarioCodigo(26), 
        dicionario.retornaTokenDicionarioCodigo(69)));

    regras.add(57, new Regra(
        dicionario.retornaTokenDicionarioCodigo(32), 
        dicionario.retornaTokenDicionarioCodigo(69)));

    regras.add(58, new Regra(
        dicionario.retornaTokenDicionarioCodigo(30), 
        dicionario.retornaTokenDicionarioCodigo(69)));

    regras.add(59, new Regra(
        dicionario.retornaTokenDicionarioCodigo(5)));

    regras.add(60, new Regra(
        dicionario.retornaTokenDicionarioCodigo(6)));

    regras.add(61, new Regra(
        dicionario.retornaTokenDicionarioCodigo(9)));

    regras.add(62, new Regra(
        dicionario.retornaTokenDicionarioCodigo(8)));

    regras.add(63, new Regra(
        dicionario.retornaTokenDicionarioCodigo(7), 
        dicionario.retornaTokenDicionarioCodigo(104)));

    regras.add(64, new Regra(
        dicionario.retornaTokenDicionarioCodigo(16), 
        dicionario.retornaTokenDicionarioCodigo(43),
        dicionario.retornaTokenDicionarioCodigo(7), 
        dicionario.retornaTokenDicionarioCodigo(104),
        dicionario.retornaTokenDicionarioCodigo(29), 
        dicionario.retornaTokenDicionarioCodigo(69),
        dicionario.retornaTokenDicionarioCodigo(37), 
        dicionario.retornaTokenDicionarioCodigo(7),
        dicionario.retornaTokenDicionarioCodigo(104), 
        dicionario.retornaTokenDicionarioCodigo(67),
        dicionario.retornaTokenDicionarioCodigo(37), 
        dicionario.retornaTokenDicionarioCodigo(70),
        dicionario.retornaTokenDicionarioCodigo(42),
        dicionario.retornaTokenDicionarioCodigo(36), 
        dicionario.retornaTokenDicionarioCodigo(61),
        dicionario.retornaTokenDicionarioCodigo(37),
        dicionario.retornaTokenDicionarioCodigo(62), 
        dicionario.retornaTokenDicionarioCodigo(35)));

    regras.add(65, new Regra(
        dicionario.retornaTokenDicionarioCodigo(33), 
        dicionario.retornaTokenDicionarioCodigo(5)));

    regras.add(66, new Regra(
        dicionario.retornaTokenDicionarioCodigo(46), 
        dicionario.retornaTokenDicionarioCodigo(5)));

    regras.add(67, new Regra(
        dicionario.retornaTokenDicionarioCodigo(20), 
        dicionario.retornaTokenDicionarioCodigo(36),
        dicionario.retornaTokenDicionarioCodigo(61), 
        dicionario.retornaTokenDicionarioCodigo(37),
        dicionario.retornaTokenDicionarioCodigo(62),
        dicionario.retornaTokenDicionarioCodigo(35), 
        dicionario.retornaTokenDicionarioCodigo(1),
        dicionario.retornaTokenDicionarioCodigo(43), 
        dicionario.retornaTokenDicionarioCodigo(7),
        dicionario.retornaTokenDicionarioCodigo(104), 
        dicionario.retornaTokenDicionarioCodigo(67),
        dicionario.retornaTokenDicionarioCodigo(42)));

    regras.add(68, new Regra(
        dicionario.retornaTokenDicionarioCodigo(22), 
        dicionario.retornaTokenDicionarioCodigo(25),
        dicionario.retornaTokenDicionarioCodigo(7), 
        dicionario.retornaTokenDicionarioCodigo(104)));

    regras.add(69, new Regra(
        dicionario.retornaTokenDicionarioCodigo(21), 
        dicionario.retornaTokenDicionarioCodigo(31),
        dicionario.retornaTokenDicionarioCodigo(11), 
        dicionario.retornaTokenDicionarioCodigo(71)));

    regras.add(70, new Regra(
        dicionario.retornaTokenDicionarioCodigo(15)));

    regras.add(71, new Regra(
        dicionario.retornaTokenDicionarioCodigo(31), 
        dicionario.retornaTokenDicionarioCodigo(7),
        dicionario.retornaTokenDicionarioCodigo(104),
        dicionario.retornaTokenDicionarioCodigo(72), 
        dicionario.retornaTokenDicionarioCodigo(71)));

    regras.add(72, new Regra(
        dicionario.retornaTokenDicionarioCodigo(31), 
        dicionario.retornaTokenDicionarioCodigo(11),
        dicionario.retornaTokenDicionarioCodigo(71)));

    regras.add(73, new Regra(
        dicionario.retornaTokenDicionarioCodigo(15)));

    regras.add(74, new Regra(
        dicionario.retornaTokenDicionarioCodigo(40), 
        dicionario.retornaTokenDicionarioCodigo(7),
        dicionario.retornaTokenDicionarioCodigo(104),
        dicionario.retornaTokenDicionarioCodigo(72)));

    regras.add(75, new Regra(
        dicionario.retornaTokenDicionarioCodigo(73), 
        dicionario.retornaTokenDicionarioCodigo(74)));

    regras.add(76, new Regra(
        dicionario.retornaTokenDicionarioCodigo(24),
        dicionario.retornaTokenDicionarioCodigo(7),
        dicionario.retornaTokenDicionarioCodigo(104),
        dicionario.retornaTokenDicionarioCodigo(64)));

    regras.add(77, new Regra(
        dicionario.retornaTokenDicionarioCodigo(34), 
        dicionario.retornaTokenDicionarioCodigo(73),
        dicionario.retornaTokenDicionarioCodigo(74)));

    regras.add(78, new Regra(
        dicionario.retornaTokenDicionarioCodigo(47), 
        dicionario.retornaTokenDicionarioCodigo(73),
        dicionario.retornaTokenDicionarioCodigo(74)));

    regras.add(79, new Regra(
        dicionario.retornaTokenDicionarioCodigo(15)));

    regras.add(80, new Regra(
        dicionario.retornaTokenDicionarioCodigo(75), 
        dicionario.retornaTokenDicionarioCodigo(76)));

    regras.add(81, new Regra(
        dicionario.retornaTokenDicionarioCodigo(15)));

    regras.add(82, new Regra(
        dicionario.retornaTokenDicionarioCodigo(41), 
        dicionario.retornaTokenDicionarioCodigo(75),
        dicionario.retornaTokenDicionarioCodigo(76)));

    regras.add(83, new Regra(
        dicionario.retornaTokenDicionarioCodigo(39), 
        dicionario.retornaTokenDicionarioCodigo(75),
        dicionario.retornaTokenDicionarioCodigo(76)));

    regras.add(84, new Regra(
        dicionario.retornaTokenDicionarioCodigo(5), 
        dicionario.retornaTokenDicionarioCodigo(102)));

    regras.add(85, new Regra(
        dicionario.retornaTokenDicionarioCodigo(6), 
        dicionario.retornaTokenDicionarioCodigo(102)));

    regras.add(86, new Regra(
        dicionario.retornaTokenDicionarioCodigo(7), 
        dicionario.retornaTokenDicionarioCodigo(104),
        dicionario.retornaTokenDicionarioCodigo(102)));

    regras.add(87, new Regra(
        dicionario.retornaTokenDicionarioCodigo(9), 
        dicionario.retornaTokenDicionarioCodigo(102)));

    regras.add(88, new Regra(
        dicionario.retornaTokenDicionarioCodigo(8), 
        dicionario.retornaTokenDicionarioCodigo(102)));

    regras.add(89, new Regra(
        dicionario.retornaTokenDicionarioCodigo(43), 
        dicionario.retornaTokenDicionarioCodigo(63),
        dicionario.retornaTokenDicionarioCodigo(42)));
  }

  public ArrayList<Regra> getRegras() {
    return regras;
  }
}
