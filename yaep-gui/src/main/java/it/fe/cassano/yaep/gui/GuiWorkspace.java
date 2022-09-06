package it.fe.cassano.yaep.gui;

import java.util.LinkedList;

import org.apache.commons.collections4.map.LinkedMap;

import it.fe.cassano.yaep.workspace.IWorkspace;

public class GuiWorkspace implements IWorkspace {

    private final String id;
    private LinkedMap<String, Object> env;
    private LinkedList<String> expressions;
    private LinkedMap<String, Object> fun;

    public GuiWorkspace(String id) {
        this.id = id;
        this.env = new LinkedMap<>();
        this.expressions = new LinkedList<>();
        this.fun = new LinkedMap<>();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public LinkedMap<String, Object> getEnv() {
        return this.env;
    }

    @Override
    public void setEnv(LinkedMap<String, Object> env) {
        this.env = env;
    }

    @Override
    public LinkedList<String> getHistory() {
        // TODO Auto-generated method stub
        return this.expressions;
    }

    @Override
    public void setHistory(LinkedList<String> expressions) {
        this.expressions = expressions;
        
    }

    @Override
    public LinkedMap<String, Object> getFun() {
        return this.fun;
    }

    @Override
    public void setFun(LinkedMap<String, Object> fun) {
        this.fun = fun;
    }
    
}