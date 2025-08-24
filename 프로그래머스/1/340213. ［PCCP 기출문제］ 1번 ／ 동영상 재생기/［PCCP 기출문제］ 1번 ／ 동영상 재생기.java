class Solution {
    
    public String solution(String videoLen, String pos, String opStart, String opEnd, String[] commands) {
        int secPos = timeToSec(pos);
        for (String command : commands) {
            if (timeToSec(opStart) <= secPos && secPos <= timeToSec(opEnd)) {
                secPos = timeToSec(opEnd);
            }
            
            if (command.equals("prev")) {
                secPos = Math.max(secPos - 10, 0);
            } else if (command.equals("next")) {
                secPos = Math.min(secPos + 10, timeToSec(videoLen));
            }
        }
        
        if (timeToSec(opStart) <= secPos && secPos <= timeToSec(opEnd)) {
            secPos = timeToSec(opEnd);
        }
        
        return secToTime(secPos);
    }
    
    public int timeToSec(String time) {
        String[] split = time.split(":");
        int minute = Integer.parseInt(split[0]);
        return Integer.parseInt(split[1]) + minute * 60;
    }
    
    public String secToTime(int sec) {
        int minute = sec / 60;
        int second = sec % 60;
        StringBuilder sb = new StringBuilder();
        if (minute < 10) {
            sb.append("0" + minute);
        } else {
            sb.append(minute);
        }
        
        sb.append(":");
        
        if (second < 10) {
            sb.append("0" + second);
        } else {
            sb.append(second);
        }
        
        return sb.toString();
    }
}