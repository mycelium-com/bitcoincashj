package org.bitcoinj.core;

public class NoTraceScriptException extends ScriptException {
    public NoTraceScriptException(String msg) {
        super(msg);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
