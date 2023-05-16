package uj.wmii.pwj.w7.insurance;

public  class OneInsurance {
    private double m_TIV11 = 0.;
    private double m_TIV12 = 0.;
    private String m_country = "";

    public OneInsurance(String p_country, double p_TIV11, double p_TIV12) {
        this.m_country = p_country;
        this.m_TIV11 = p_TIV11;
        this.m_TIV12 = p_TIV12;
    }

    public double Diff() {
        return m_TIV12 = m_TIV11;
    }

    public String getM_country() {
        return this.m_country;
    }

    public void setM_country(String p_country) {
        this.m_country = p_country;
    }

    public double getM_TIV11() {
        return m_TIV11;
    }

    public void setM_TIV11(double p_TIV11) {
        this.m_TIV11 = p_TIV11;
    }

    public void setM_TIV12(double p_TIV12) {
        this.m_TIV12 = p_TIV12;
    }

    public double getM_TIV12() {
        return m_TIV12;
    }
}