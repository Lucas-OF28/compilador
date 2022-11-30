package Classes;

import java.util.ArrayList;

public class Regra {

    ArrayList<Token> regra = new ArrayList<Token>();
    //Gus: you gotta be kiddin' me

    public Regra(Token ... tokens) {
        for(Token token: tokens){
            regra.add(token);
        }
    }

    // public Regra(Token token0, Token token1) {
    //     regra.add(token0);
    //     regra.add(token1);
    // }

    // public Regra(Token token0, Token token1, Token token2) {
    //     regra.add(token0);
    //     regra.add(token1);
    //     regra.add(token2);
    // }

    // public Regra(Token token0, Token token1, Token token2, Token token3) {
    //     regra.add(token0);
    //     regra.add(token1);
    //     regra.add(token2);
    //     regra.add(token3);
    // }

    // public Regra(Token token0, Token token1, Token token2, Token token3, Token token4) {
    //     regra.add(token0);
    //     regra.add(token1);
    //     regra.add(token2);
    //     regra.add(token3);
    //     regra.add(token4);
    // }

    // public Regra(Token token0, Token token1, Token token2, Token token3, Token token4, Token token5) {
    //     regra.add(token0);
    //     regra.add(token1);
    //     regra.add(token2);
    //     regra.add(token3);
    //     regra.add(token4);
    //     regra.add(token5);
    // }

    // public Regra(Token token0, Token token1, Token token2, Token token3, Token token4, Token token5, Token token6) {
    //     regra.add(token0);
    //     regra.add(token1);
    //     regra.add(token2);
    //     regra.add(token3);
    //     regra.add(token4);
    //     regra.add(token5);
    //     regra.add(token6);
    // }

    // public Regra(Token token0, Token token1, Token token2, Token token3, Token token4, Token token5, Token token6,
    //         Token token7) {
    //     regra.add(token0);
    //     regra.add(token1);
    //     regra.add(token2);
    //     regra.add(token3);
    //     regra.add(token4);
    //     regra.add(token5);
    //     regra.add(token6);
    //     regra.add(token7);

    // }

    // public Regra(Token token0, Token token1, Token token2, Token token3, Token token4, Token token5, Token token6,
    //         Token token7, Token token8) {
    //     regra.add(token0);
    //     regra.add(token1);
    //     regra.add(token2);
    //     regra.add(token3);
    //     regra.add(token4);
    //     regra.add(token5);
    //     regra.add(token6);
    //     regra.add(token7);
    //     regra.add(token8);

    // }

    // public Regra(Token token0, Token token1, Token token2, Token token3, Token token4, Token token5, Token token6,
    //         Token token7, Token token8, Token token9) {
    //     regra.add(token0);
    //     regra.add(token1);
    //     regra.add(token2);
    //     regra.add(token3);
    //     regra.add(token4);
    //     regra.add(token5);
    //     regra.add(token6);
    //     regra.add(token7);
    //     regra.add(token8);
    //     regra.add(token9);
    // }

    // public Regra(Token token0, Token token1, Token token2, Token token3, Token token4, Token token5, Token token6,
    //         Token token7, Token token8, Token token9, Token token10) {
    //     regra.add(token0);
    //     regra.add(token1);
    //     regra.add(token2);
    //     regra.add(token3);
    //     regra.add(token4);
    //     regra.add(token5);
    //     regra.add(token6);
    //     regra.add(token7);
    //     regra.add(token8);
    //     regra.add(token9);
    //     regra.add(token10);
    // }

    // public Regra(Token token0, Token token1, Token token2, Token token3, Token token4, Token token5, Token token6,
    //         Token token7, Token token8, Token token9, Token token10, Token token11) {
    //     regra.add(token0);
    //     regra.add(token1);
    //     regra.add(token2);
    //     regra.add(token3);
    //     regra.add(token4);
    //     regra.add(token5);
    //     regra.add(token6);
    //     regra.add(token7);
    //     regra.add(token8);
    //     regra.add(token9);
    //     regra.add(token10);
    //     regra.add(token11);
    // }

    // public Regra(Token token0, Token token1, Token token2, Token token3, Token token4, Token token5, Token token6,
    //         Token token7, Token token8, Token token9, Token token10, Token token11, Token token12) {
    //     regra.add(token0);
    //     regra.add(token1);
    //     regra.add(token2);
    //     regra.add(token3);
    //     regra.add(token4);
    //     regra.add(token5);
    //     regra.add(token6);
    //     regra.add(token7);
    //     regra.add(token8);
    //     regra.add(token9);
    //     regra.add(token10);
    //     regra.add(token11);
    //     regra.add(token12);
    // }

    // public Regra(Token token0, Token token1, Token token2, Token token3, Token token4, Token token5, Token token6,
    //         Token token7, Token token8, Token token9, Token token10, Token token11, Token token12, Token token13) {
    //     regra.add(token0);
    //     regra.add(token1);
    //     regra.add(token2);
    //     regra.add(token3);
    //     regra.add(token4);
    //     regra.add(token5);
    //     regra.add(token6);
    //     regra.add(token7);
    //     regra.add(token8);
    //     regra.add(token9);
    //     regra.add(token10);
    //     regra.add(token11);
    //     regra.add(token12);
    //     regra.add(token13);
    // }

    // public Regra(Token token0, Token token1, Token token2, Token token3, Token token4, Token token5, Token token6,
    //         Token token7, Token token8, Token token9, Token token10, Token token11, Token token12, Token token13,
    //         Token token14) {
    //     regra.add(token0);
    //     regra.add(token1);
    //     regra.add(token2);
    //     regra.add(token3);
    //     regra.add(token4);
    //     regra.add(token5);
    //     regra.add(token6);
    //     regra.add(token7);
    //     regra.add(token8);
    //     regra.add(token9);
    //     regra.add(token10);
    //     regra.add(token11);
    //     regra.add(token12);
    //     regra.add(token13);
    //     regra.add(token14);
    // }

    // public Regra(Token token0, Token token1, Token token2, Token token3, Token token4, Token token5, Token token6,
    //         Token token7, Token token8, Token token9, Token token10, Token token11, Token token12, Token token13,
    //         Token token14, Token token15) {
    //     regra.add(token0);
    //     regra.add(token1);
    //     regra.add(token2);
    //     regra.add(token3);
    //     regra.add(token4);
    //     regra.add(token5);
    //     regra.add(token6);
    //     regra.add(token7);
    //     regra.add(token8);
    //     regra.add(token9);
    //     regra.add(token10);
    //     regra.add(token11);
    //     regra.add(token12);
    //     regra.add(token13);
    //     regra.add(token14);
    //     regra.add(token15);
    // }

    // public Regra(Token token0, Token token1, Token token2, Token token3, Token token4, Token token5, Token token6,
    //         Token token7, Token token8, Token token9, Token token10, Token token11, Token token12, Token token13,
    //         Token token14, Token token15, Token token16) {
    //     regra.add(token0);
    //     regra.add(token1);
    //     regra.add(token2);
    //     regra.add(token3);
    //     regra.add(token4);
    //     regra.add(token5);
    //     regra.add(token6);
    //     regra.add(token7);
    //     regra.add(token8);
    //     regra.add(token9);
    //     regra.add(token10);
    //     regra.add(token11);
    //     regra.add(token12);
    //     regra.add(token13);
    //     regra.add(token14);
    //     regra.add(token15);
    //     regra.add(token16);
    // }

    // public Regra(Token token0, Token token1, Token token2, Token token3, Token token4, Token token5, Token token6,
    //         Token token7, Token token8, Token token9, Token token10, Token token11, Token token12, Token token13,
    //         Token token14, Token token15, Token token16, Token token17) {
    //     regra.add(token0);
    //     regra.add(token1);
    //     regra.add(token2);
    //     regra.add(token3);
    //     regra.add(token4);
    //     regra.add(token5);
    //     regra.add(token6);
    //     regra.add(token7);
    //     regra.add(token8);
    //     regra.add(token9);
    //     regra.add(token10);
    //     regra.add(token11);
    //     regra.add(token12);
    //     regra.add(token13);
    //     regra.add(token14);
    //     regra.add(token15);
    //     regra.add(token16);
    //     regra.add(token17);
    // }

    public ArrayList<Token> getRegra() {
        return regra;
    }
}
