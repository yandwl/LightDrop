package me.neiizun.lightdrop.registion;

import me.neiizun.lightdrop.LightDrop;
import me.neiizun.lightdrop.command.Command;
import org.reflections8.Reflections;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public final class Runner {
    public static final void load(Class<?> mainClassEntry,BotProvider[] botNamesAndJDA , String... args) {

        Collection<Class<?>> commandBeans = new Reflections(mainClassEntry).getTypesAnnotatedWith(CommandCluster.class);
        commandBeans.parallelStream().forEach(Runner::scanAndGraph);

    }


    private static ConcurrentHashMap<String, LightDrop> bots = new ConcurrentHashMap<>();

    private static void scanAndGraph(Class<?> s) {
        String botname = s.getAnnotation(CommandCluster.class).forBot();
        if (!bots.containsKey(botname)) {
            bots.put(botname, new LightDrop());
        }
        try {
            bots.get(botname).map(s.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println("could not getInstance for: " + s.getName()+ "\n should have a public Constructor\n"+e.toString());
        }
    }

    public static LightDrop getBot(String name){
        return bots.get(name);
    }

}
