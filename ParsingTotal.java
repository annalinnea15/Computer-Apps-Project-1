import java.util.ArrayList;

import org.w3c.dom.NodeList;


public class ParsingTotal {
	

	public static void main(String[] args) {
		
		//Parse the EQ file
		ParseEQ PEQ = new ParseEQ("MicroGridTestConfiguration_T1_BE_EQ_V2.xml"); 
		//make an array for each category to store the data in 
		ArrayList<BaseVoltage> baseVoltageArray = PEQ.makeBaseVoltage();
		ArrayList<Substation> substationArray = PEQ.makeSubstation();
		ArrayList<VoltageLevel> voltageLevelArray = PEQ.makeVoltageLevel();
		ArrayList<GeneratingUnit> generatingUnitArray = PEQ.makeGeneratingUnit();
		ArrayList<SynchronousMachine> synchronousMachineArray = PEQ.makeSynchronousMachine();
		ArrayList<RegulatingControl> regulatingControlArray = PEQ.makeRegulatingControl();
		ArrayList<PowerTransformer> powerTransformerArray = PEQ.makePowerTransformer();
		ArrayList<EnergyConsumer> energyConsumerArray = PEQ.makeEnergyConsumer();
		ArrayList<PowerTransformerEnd> powerTransformerEndArray = PEQ.makePowerTransformerEnd();
		ArrayList<Breaker> breakerArray = PEQ.makeBreaker();
		ArrayList<RatioTapChanger> ratioTapChangerArray = PEQ.makeRatioTapChanger();
		ArrayList<ACLine> ACLineArray = PEQ.makeACLine();
		ArrayList<ConnectivityNode> ConnectivityNodeArray = PEQ.makeConnectivityNode();
		ArrayList<Terminal> TerminalArray = PEQ.makeTerminal();
		
		
		//Parse the SSH file and have it add on to the arrays
		ParseSSH PSSH = new ParseSSH("MicroGridTestConfiguration_T1_BE_SSH_V2.xml"); 
		synchronousMachineArray = PSSH.makeSynchronousMachine(synchronousMachineArray);
		regulatingControlArray = PSSH.makeRegulatingControl(regulatingControlArray);
		energyConsumerArray = PSSH.makeEnergyConsumer(energyConsumerArray);
		breakerArray = PSSH.makeBreaker(breakerArray);
		ratioTapChangerArray = PSSH.makeRatioTapChanger(ratioTapChangerArray);
		
		//yay! Everything is parsed
		
		
	}

}
