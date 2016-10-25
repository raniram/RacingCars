package com.mygdx.game;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.audio.Sound;

public class EnemyCar extends Actor {
	private Rectangle bounds = new Rectangle();
	private Sound crash;
	
	public EnemyCar(float x, float y) {
		setWidth(300);
		setHeight(120);
		setPosition(x, y - getHeight()/2);

		int rnd = MathUtils.random(0, 3);
		if (rnd == 0) setColor(Color.RED);
		if (rnd == 1) setColor(Color.GREEN);
		if (rnd == 2) setColor(Color.WHITE);
		if (rnd == 3) setColor(Color.BLUE);
		
		addAction(moveTo(-getWidth(), getY(), MathUtils.random(4.0f, 6.0f)));

		crash= Gdx.audio.newSound(Gdx.files.internal("crash.wav"));
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		updateBounds();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(Assets.car, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}
	
	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}
	
	public void crash(boolean front, boolean above) {
		clearActions();
		crash.play();
		addAction(fadeOut(1f));
		if (front && above)
			addAction(parallel(rotateTo(-360, 1.5f),moveTo(200, 200, 1.5f),removeActor()
			));

		if (front && !above)
			addAction(sequence(parallel(rotateTo(360, 1.5f), moveTo(200, -200, 1.5f)), removeActor()
			));
		if (!front && above) addAction(sequence(parallel(rotateTo(360, 1.5f), moveTo(-200, 200, 1.5f)), removeActor()));
		if (!front && !above) addAction(sequence(parallel(rotateTo(-360, 1.5f), moveTo(-200, -200, 1.5f)), removeActor()));

	}

	public Rectangle getBounds() {
		return bounds;
	}
}
