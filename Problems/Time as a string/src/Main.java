class Time {

    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public String toString(){
        String h =  hours<10 ? "0".concat(String.valueOf(hours)) : ""+hours;
        String m =  minutes<10 ? "0".concat(String.valueOf(minutes)) : ""+minutes;
        String s =  seconds<10 ? "0".concat(String.valueOf(seconds)) : ""+seconds;
        return h.concat(":").concat(m).concat(":").concat(s);
    }
}