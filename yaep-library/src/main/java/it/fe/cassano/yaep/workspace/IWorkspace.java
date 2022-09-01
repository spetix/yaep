package it.fe.cassano.yaep.workspace;

import java.util.LinkedList;

import org.apache.commons.collections4.map.LinkedMap;

public interface IWorkspace {

  public String getId();

  public LinkedMap<String, String> getEnv();

  public void setEnv(LinkedMap<String, String> env);

  public LinkedList<String> getExpressions();

  public void setExpressions(LinkedList<String> expressions);

  public LinkedMap<String, String> getFun();

  public void setFun(LinkedMap<String, String> fun);


    
}

	



