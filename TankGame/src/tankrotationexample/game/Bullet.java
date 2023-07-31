package tankrotationexample.game;

import tankrotationexample.GameConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject {

    private float x;
    private float y;
    private float vx;
    private float vy;

    private float charge = 1f;

    private float angle;

    private float R = 4;

    private BufferedImage img;

    private Rectangle hitbox;

    private boolean visible;
    private int damage;

    public Bullet(float x, float y, float angle, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.vx =0;
        this.vy = 0;
        this.angle = angle;
        this.visible = true;
        this.damage = 15;

        this.hitbox= new Rectangle((int)x, (int)y, this.img.getWidth(), this.img.getHeight());
    }
    public Rectangle getHitbox() {
        return this.hitbox.getBounds();
    }

    @Override
    public void collides(GameObject obj2) {
        if(obj2 instanceof Wall) {
            this.visible = false;
        }

        if(obj2 instanceof Tank) {

            ((Tank) obj2).getShot();
            this.visible = false;
        }
    }

    public void increaseCharge() {
        this.charge = this.charge + 0.05f;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }


    void update() {
        vx = Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
        this.hitbox.setLocation((int)x,(int)y);
    }



    private void checkBorder() {
        if ((x < 30) || (x >= GameConstants.GAME_WORLD_WIDTH - 88)) {
            this.visible = false;
        }
        if ((y < 30) || (y >= GameConstants.GAME_WORLD_HEIGHT - 88)) {
            this.visible = false;
        }


    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        rotation.scale(this.charge,this.charge);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);


    }
    //for charging bullets
//    public void setHeading(float x, float y, float angle) {
//        this.x =x;
//        this.y =y;
//        this.angle = angle;
//
//    }


    public boolean isVisible() {
        return this.visible;
    }
    public BufferedImage getImage() {
        return img;
    }

    public void setImage(BufferedImage img) {
        this.img = img;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
