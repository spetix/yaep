package it.fe.cassano.yaep.workspace;

import java.util.LinkedList;

import org.apache.commons.collections4.map.LinkedMap;

public interface IWorkspace {

  public String getId();

  public LinkedMap<String, Object> getEnv();

  public void setEnv(LinkedMap<String, Object> env);

  public LinkedList<String> getHistory();

  public void setHistory(LinkedList<String> expressions);

  public LinkedMap<String, Object> getFun();

  public void setFun(LinkedMap<String, Object> fun);


    
}

	



