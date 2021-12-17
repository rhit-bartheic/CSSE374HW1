import java.io.Serializable;

public class Tax implements Serializable {
    public Tax() {
        
    }

    public float getTax(State state) {
        if(State.AK.equals(state)) return (float) 1.01;
        if(State.AL.equals(state)) return (float) 1.02;
        if(State.AR.equals(state)) return (float) 1.03;
        if(State.AZ.equals(state)) return (float) 1.04;
        if(State.CA.equals(state)) return (float) 1.05;
        if(State.CO.equals(state)) return (float) 1.06;
        if(State.CT.equals(state)) return (float) 1.07;
        if(State.DE.equals(state)) return (float) 1.08;
        if(State.FL.equals(state)) return (float) 1.09;
        if(State.GA.equals(state)) return (float) 1.10;
        if(State.HI.equals(state)) return (float) 1.11;
        if(State.IA.equals(state)) return (float) 1.12;
        if(State.ID.equals(state)) return (float) 1.13;
        if(State.IL.equals(state)) return (float) 1.14;
        if(State.IN.equals(state)) return (float) 1.15;
        if(State.KS.equals(state)) return (float) 1.16;
        if(State.KY.equals(state)) return (float) 1.17;
        if(State.LA.equals(state)) return (float) 1.18;
        if(State.MA.equals(state)) return (float) 1.19;
        if(State.MD.equals(state)) return (float) 1.20;
        if(State.ME.equals(state)) return (float) 1.21;
        if(State.MI.equals(state)) return (float) 1.22;
        if(State.MN.equals(state)) return (float) 1.23;
        if(State.MO.equals(state)) return (float) 1.24;
        if(State.MS.equals(state)) return (float) 1.25;
        if(State.MT.equals(state)) return (float) 1.26;
        if(State.NC.equals(state)) return (float) 1.27;
        if(State.ND.equals(state)) return (float) 1.28;
        if(State.NE.equals(state)) return (float) 1.29;
        if(State.NH.equals(state)) return (float) 1.30;
        if(State.NJ.equals(state)) return (float) 1.31;
        if(State.NM.equals(state)) return (float) 1.32;
        if(State.NV.equals(state)) return (float) 1.33;
        if(State.NY.equals(state)) return (float) 1.34;
        if(State.OH.equals(state)) return (float) 1.35;
        if(State.OK.equals(state)) return (float) 1.36;
        if(State.OR.equals(state)) return (float) 1.37;
        if(State.PA.equals(state)) return (float) 1.38;
        if(State.RI.equals(state)) return (float) 1.39;
        if(State.SC.equals(state)) return (float) 1.40;
        if(State.SD.equals(state)) return (float) 1.41;
        if(State.TN.equals(state)) return (float) 1.42;
        if(State.TX.equals(state)) return (float) 1.43;
        if(State.UT.equals(state)) return (float) 1.44;
        if(State.VA.equals(state)) return (float) 1.45;
        if(State.VT.equals(state)) return (float) 1.46;
        if(State.WA.equals(state)) return (float) 1.47;
        if(State.WI.equals(state)) return (float) 1.48;
        if(State.WV.equals(state)) return (float) 1.49;
        return (float) 1.5;
    }
}
