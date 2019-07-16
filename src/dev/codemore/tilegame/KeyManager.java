package dev.codemore.tilegame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class KeyManager implements KeyListener {

    private final Map<Integer, Collection<Consumer<KeyEvent>>> keySpecificPressedConsumer = new ConcurrentHashMap<>();
    private final Map<Integer, Collection<Consumer<KeyEvent>>> keySpecificReleasedConsumer = new ConcurrentHashMap<>();
    private final Map<Integer, Collection<Consumer<KeyEvent>>> keySpecificTypedConsumer = new ConcurrentHashMap<>();
    private final List<KeyEvent> pressedKeys = new CopyOnWriteArrayList<>();
    private final List<KeyEvent> releasedKeys = new CopyOnWriteArrayList<>();
    private final List<KeyEvent> typedKeys = new CopyOnWriteArrayList<>();

    public void tick(){
        this.executePressedKeys();
        this.executeReleasedKeys();
        this.executeTypedKeys();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.pressedKeys.stream().anyMatch(key -> key.getKeyCode() == e.getKeyCode())) {
            return;
        }
        this.pressedKeys.add(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        // Remove pressed key
        for (final KeyEvent removeKey : this.pressedKeys) {
            if (removeKey.getKeyCode() == e.getKeyCode()) {
                this.pressedKeys.remove(removeKey);
            }
        }

        // Add typed key
        keyTyped(e);

        // Add released key
        if (this.releasedKeys.stream().anyMatch(key -> key.getKeyCode() == e.getKeyCode())) {
            return;
        }
        this.releasedKeys.add(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (this.typedKeys.stream().anyMatch(key -> key.getKeyCode() == e.getKeyCode())) {
            return;
        }
        this.typedKeys.add(e);
    }

    public void onKeyPressed(final int keyCode, final Consumer<KeyEvent> consumer) {
        this.keySpecificPressedConsumer.computeIfAbsent(keyCode, ConcurrentHashMap::newKeySet).add(consumer);
    }

    public void onKeyReleased(final int keyCode, final Consumer<KeyEvent> consumer) {
        this.keySpecificReleasedConsumer.computeIfAbsent(keyCode, ConcurrentHashMap::newKeySet).add(consumer);
    }

    public void onKeyTyped(final int keyCode, final Consumer<KeyEvent> consumer) {
        this.keySpecificTypedConsumer.computeIfAbsent(keyCode, ConcurrentHashMap::newKeySet).add(consumer);
    }

    private void executePressedKeys() {
        this.pressedKeys.forEach(key -> {
            this.keySpecificPressedConsumer.getOrDefault(key.getKeyCode(), Collections.emptySet()).forEach(consumer -> consumer.accept(key));
        });
    }

    private void executeReleasedKeys() {
        this.releasedKeys.forEach(key -> {
            this.keySpecificReleasedConsumer.getOrDefault(key.getKeyCode(), Collections.emptySet()).forEach(consumer -> consumer.accept(key));
        });
        this.releasedKeys.clear();
    }

    private void executeTypedKeys() {
        this.typedKeys.forEach(key -> {
            this.keySpecificTypedConsumer.getOrDefault(key.getKeyCode(), Collections.emptySet()).forEach(consumer -> consumer.accept(key));
        });
        this.typedKeys.clear();
    }

}
