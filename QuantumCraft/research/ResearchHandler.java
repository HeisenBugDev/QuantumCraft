package mods.QuantumCraft.research;

public class ResearchHandler {

	public ResearchHandler() {
		// TODO Auto-generated constructor stub
	}

	public boolean hasRIUnlocked(ResearchItem ri)
	{
		if (ri.riDescription.matches("ASFKGaiuagds")) return true; else return false;
	}
	
	public boolean canUnlockRI(ResearchItem ri)
	{
		if (ri.parentAchievement != null)
		{
			return hasRIUnlocked(ri.parentAchievement) ? true : false;
		} 
		return true;
	}
	
}
