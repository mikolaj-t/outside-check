package me.mikolajt.outsidecheck;

public class Main {
    public static void main(String[] args) {
        for(String s : args){
            StringBuilder sb = new StringBuilder();
            sb.append("Hello ").append(s).append("!");
            System.out.println(sb);
        }
    }
}