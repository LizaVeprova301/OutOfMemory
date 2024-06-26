package com.outofmemory.game;

import com.outofmemory.game.Heroes.Player;
import com.outofmemory.game.Tools.Joystick;
import com.outofmemory.game.Tools.Point2D;
import com.badlogic.gdx.InputProcessor;

public class TouchProcessor implements InputProcessor {
    private final InputState inputState;
    public Joystick joy;
    public Joystick joyBullet;

    public TouchProcessor(InputState inputState) {
        this.inputState = inputState;
        this.joy = new Joystick(Main.circle, Main.capibara, new Point2D(((Main.screenHeight / 3) / 2 + (Main.screenHeight / 3) / 4), (Main.screenHeight / 3) / 2 + (Main.screenHeight / 3) / 4), Main.screenHeight / 3);
        this.joyBullet = new Joystick(Main.circle, Main.capibara, new Point2D(Main.screenWidth - ((Main.screenHeight / 3) / 2 + (Main.screenHeight / 3) / 4), (Main.screenHeight / 3) / 2 + (Main.screenHeight / 3) / 4), Main.screenHeight / 3);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenY = Main.screenHeight - screenY;
        multitouch((int)screenX, (int)screenY, true, pointer);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenY = Main.screenHeight - screenY;
        multitouch((int)screenX, (int)screenY, false, pointer);
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        screenY = Main.screenHeight - screenY;
        multitouch((int)screenX, (int)screenY, false, pointer);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        screenY = Main.screenHeight - screenY;
        multitouch((int)screenX, (int)screenY, true, pointer);
        return false;
    }

    public void multitouch(float x, float y, boolean isDownTouch, int pointer) {
        for (int i = 0; i < 3; i++) {
            joy.update(x, y, isDownTouch, pointer);
            joyBullet.update(x, y, isDownTouch, pointer);
        }
    }

    public InputState updateAndGetInputState(Player player) {
        inputState.setType("playerState");
        inputState.setX(player.getBody().getPosition().x);
        inputState.setY(player.getBody().getPosition().y);
        return inputState;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
