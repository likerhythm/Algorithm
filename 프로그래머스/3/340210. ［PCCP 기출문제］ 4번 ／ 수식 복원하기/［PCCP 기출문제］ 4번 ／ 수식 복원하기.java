import java.util.*;
import java.io.*;

class Solution {
    
    static class Expression {
        String operator;
        String[] operands;
        String answer;
        
        public Expression(String operator, String[] operands, String answer) {
            this.operator = operator;
            this.operands = operands;
            this.answer = answer;
        }
        
        // 2~9 리턴. 리턴값이 -1이면 진버을 특정할 수 없는 수식
        public int degree() {
            if (answer.equals("X")) return -1;
            
            for (int degree = 2; degree <= 9; degree++) {
                int num1 = Integer.parseInt(operands[0]);
                int num2 = Integer.parseInt(operands[1]);
                int ans = Integer.parseInt(answer);
                if (num1 + num2 == ans) return -1; // 10진수로 계산했을 때 같은 결과라면 진법 특정 불가
                if (num1 - num2 == ans) return -1;
                
                try {
                    num1 = Integer.parseInt(operands[0], degree);
                    num2 = Integer.parseInt(operands[1], degree);
                    ans = Integer.parseInt(answer, degree);
                    if (num1 + num2 == ans) return degree;
                    if (num1 - num2 == ans) return degree;
                } catch(NumberFormatException e) {
                    
                }
            }
            return -1;
        }
        
        public void setAnswer(int degree) {
            if (operator.equals("+")) {
                answer = Integer.toString(toInt(operands[0], degree) + toInt(operands[1], degree), degree);
            } else {
                answer = Integer.toString(toInt(operands[0], degree) - toInt(operands[1], degree), degree);
            }
        }
        
        private int toInt(String s) {
            return Integer.parseInt(s);
        }
        
        private int toInt(String s, int deg) {
            return Integer.parseInt(s, deg);
        }
        
        public int maxInt() {
           int max = 0;
    
            for (String op : operands) {
                for (char c : op.toCharArray()) {
                    max = Math.max(max, c - '0');
                }
            }

            if (!answer.equals("X")) {
                for (char c : answer.toCharArray()) {
                    max = Math.max(max, c - '0');
                }
            }

            return max;
        }
        
        public void setAnswerByMinDegree(int minDegree) {
            Set<String> set = new HashSet<>();
            for (int deg = minDegree; deg <= 9; deg++) {
                int num1 = Integer.parseInt(operands[0], deg);
                int num2 = Integer.parseInt(operands[1], deg);
                int ans;
                if (operator.equals("+")) ans = num1 + num2;
                else ans = num1 - num2;
                set.add(Integer.toString(ans, deg));
            }
            if (set.size() == 1) {
                this.answer = new ArrayList<>(set).get(0);
            } else {
                this.answer = "?";
            }
        }
        
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(operands[0]).append(" ").append(operator).append(" ").append(operands[1]).append(" = ").append(answer);
            return sb.toString();
        }
    }
    
    static Expression[] expressions;
    
    public String[] solution(String[] exps) {
        expressions = new Expression[exps.length];
        int idx = 0;
        for (String exp : exps) {
            String[] components = exp.split(" ");
            String[] operands = new String[] {components[0], components[2]};
            String operator = components[1];
            String answer = components[4];
            expressions[idx++] = new Expression(operator, operands, answer);
        }
        
        int degree = -1;
        for (Expression exp : expressions) {
            int deg = exp.degree();
            if (deg != -1) {
                degree = deg;
                break;
            }
        }
        
        List<String> list = new ArrayList<>();
        if (degree != -1) { // 진법을 특정한 경우
            for (Expression exp : expressions) {
                if (!exp.answer.equals("X")) continue;
                exp.setAnswer(degree);
                String s = exp.toString();
                list.add(s);
            }
        } else { // 진법을 특정하지 못한 경우
            int maxInt = 0;
            for (Expression exp : expressions) {
                maxInt = Math.max(maxInt, exp.maxInt());
            }
            int minDegree = maxInt + 1;
            for (Expression exp : expressions) {
                if (!exp.answer.equals("X")) continue;
                exp.setAnswerByMinDegree(minDegree);
                String s = exp.toString();
                list.add(s);
            }
        }
        return list.toArray(new String[0]); 
    }
}