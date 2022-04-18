package frsf.cidisi.faia.situationcalculus.pvz;

import frsf.cidisi.faia.agent.ActionFactory;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.situationcalculus.KnowledgeBase;
import frsf.cidisi.faia.exceptions.PrologConnectorException;

public class PvzLogicAgentState extends KnowledgeBase {

	public PvzLogicAgentState(String knowledgeBaseFile) throws PrologConnectorException {
		super(knowledgeBaseFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionFactory getActionFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSituationPredicate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBestActionPredicate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getExecutedActionPredicate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateState(Perception p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initState() {
		// TODO Auto-generated method stub
		
	}

}
