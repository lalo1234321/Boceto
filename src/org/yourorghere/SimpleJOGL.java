package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import static javax.media.opengl.GL.GL_LINE_LOOP;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import static java.lang.Math.cos;
import static java.lang.Math.sin;


/**
 * SimpleJOGL.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class SimpleJOGL implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Pruebas");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new SimpleJOGL());
        frame.add(canvas);
        frame.setSize(1200, 700);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, .40f,0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        // Move the "drawing cursor" around
        gl.glTranslatef(-2.5f, 0f, -6.0f);
        // Move the "drawing cursor" to another position
        gl.glTranslatef(4.0f, 1.50f, 0.0f);
        // Cabezza
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(128.0f, 0.0f, 0.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.0f, .50f, 0.0f);  // Top Left
        gl.glVertex3f(0.0f, .50f, 0.0f);   // Top Right
        gl.glVertex3f(0.0f, 0.0f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.0f, 0.0f, 0.0f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();
        //Ojo derecho
        gl.glTranslatef(-0.50f, 0.22f, 0.0f);
        float incr = (float) (2 * Math.PI / 30);

        gl.glBegin(GL.GL_TRIANGLE_FAN);

        gl.glColor3f(1.5f, 1.5f, 1.0f); 
        gl.glVertex2f(0.0f, 0.0f);

        for(int i = 0; i < 30; i++)
        {
              float angle = incr * i;

              float x = (float) Math.cos(angle) * .20f;
              float y = (float) Math.sin(angle) * .20f;

              gl.glVertex2f(x, y);
        }

        gl.glVertex2f(.20f, 0.0f);

        gl.glEnd();
            
            
        //Ojo izquierdo
        gl.glTranslatef(-.95f, 0.01f, 0.0f);



        gl.glBegin(GL.GL_TRIANGLE_FAN);

        gl.glColor3f(1.5f, 1.5f, 1.0f); 
        gl.glVertex2f(0.0f, 0.0f);

        for(int i = 0; i < 30; i++)
        {
              float angle = incr * i;

              float x = (float) Math.cos(angle) * .20f;
              float y = (float) Math.sin(angle) * .20f;

              gl.glVertex2f(x, y);
        }

        gl.glVertex2f(.20f, 0.0f);

        gl.glEnd();
            
            //Cuello
        gl.glTranslatef(.70f, -.73f, 0.0f);
        // Draw A Quad
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.5f, 1.5f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-.50f, .50f, 0.0f);  // Top Left
        gl.glVertex3f(0.0f, .50f, 0.0f);   // Top Right
        gl.glVertex3f(0.0f, -1.0f, 0.0f);  // Bottom Right
        gl.glVertex3f(-.50f, -1.0f, 0.0f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();
        
        
        //boca
        gl.glTranslatef(-0.30f, .73f, 0.0f);
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glColor3f(1.0f, 0.0f, 0.0f);    // Set the current drawing color to red
        gl.glVertex3f(0.05f, -0.60f, 0.0f);   // Top
        gl.glColor3f(0.0f, 1.0f, 0.0f);    // Set the current drawing color to green
        gl.glVertex3f(-.21f, -1.0f, 0.0f); // Bottom Left
        gl.glColor3f(0.0f, 0.0f, 1.0f);    // Set the current drawing color to blue
        gl.glVertex3f(.31f, -1.0f, 0.0f);  // Bottom Right
        // Finished Drawing The Triangle
        gl.glEnd();
        //Cuerpo bajo
        gl.glTranslatef(1.36f, -1.85f, 0.0f);
        // Draw A Quad
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(255f, 0.0f, 0.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-2.5f, .50f, 0.0f);  // Top Left
        gl.glVertex3f(0.0f, .50f, 0.0f);   // Top Right
        gl.glVertex3f(0.0f, -.30f, 0.0f);  // Bottom Right
        gl.glVertex3f(-2.5f, -.30f, 0.0f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();
        
        
        //pierna izquieerda
        gl.glTranslatef(-.30f, -.80f, 0.0f);
        // Draw A Quad
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.5f, 1.5f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-.50f, .50f, 0.0f);  // Top Left
        gl.glVertex3f(0.0f, .50f, 0.0f);   // Top Right
        gl.glVertex3f(0.0f, -1.0f, 0.0f);  // Bottom Right
        gl.glVertex3f(-.50f, -1.0f, 0.0f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();
        
        //pierna derecha
        gl.glTranslatef(-1.45f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.5f, 1.5f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-.50f, .50f, 0.0f);  // Top Left
        gl.glVertex3f(0.0f, .50f, 0.0f);   // Top Right
        gl.glVertex3f(0.0f, -1.0f, 0.0f);  // Bottom Right
        gl.glVertex3f(-.50f, -1.0f, 0.0f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();
        
        //pie izquierdo
        gl.glTranslatef(0.0f, -1.50f, 0.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(128.0f, 0.0f, 0.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-1.50f, .50f, 0.0f);  // Top Left
        gl.glVertex3f(0.0f, .50f, 0.0f);   // Top Right
        gl.glVertex3f(0.0f, 0.0f, 0.0f);  // Bottom Right
        gl.glVertex3f(-1.50f, 0.0f, 0.0f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();
        
        //pie derecho
        gl.glTranslatef(2.45f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(128.0f, 0.0f, 0.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-1.50f, .50f, 0.0f);  // Top Left
        gl.glVertex3f(0.0f, .50f, 0.0f);   // Top Right
        gl.glVertex3f(0.0f, 0.0f, 0.0f);  // Bottom Right
        gl.glVertex3f(-1.50f, 0.0f, 0.0f); //
        gl.glEnd();
        //sol
        gl.glTranslatef(.45f, 4.0f, 0.0f);
        gl.glBegin(GL.GL_TRIANGLE_FAN);

        gl.glColor3f(1.0f, .50f,  0.0f); 
        gl.glVertex2f(0.0f, 0.0f);

        for(int i = 0; i < 30; i++)
        {
              float angle = incr * i;

              float x = (float) Math.cos(angle) * .50f;
              float y = (float) Math.sin(angle) * .50f;

              gl.glVertex2f(x, y);
        }

        gl.glVertex2f(.50f, 0.0f);

        gl.glEnd();
        //Estrella
        gl.glTranslatef(-5.45f, 0.0f, 0.0f);
        gl.glColor3f(1.0f, 1.50f,  1.0f); 
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(-0.10f, .10f, 0.0f);  // Top Left
        gl.glVertex3f(0.0f, .10f, 0.0f);   // Top Right
        gl.glVertex3f(0.0f, 0.0f, 0.0f);  // Bottom Right
        gl.glVertex3f(-0.10f, 0.0f, 0.0f);
        gl.glEnd();

        gl.glTranslatef(-0.05f, 1.1f, 0.0f);
        gl.glColor3f(1.0f, 1.50f,  1.0f);
        gl.glBegin(GL.GL_TRIANGLES);         
        gl.glVertex3f(0.01f, -0.60f, 0.0f);   // Top            
        gl.glVertex3f(-.05f, -1.0f, 0.0f); // Bottom Left
        gl.glVertex3f(.05f, -1.0f, 0.0f);  // Bottom Right
        // Finished Drawing The Triangle
        gl.glEnd();
        
        gl.glTranslatef(-.03f, -1.05f, 0.0f);
        gl.glColor3f(1.0f, 1.90f,  1.0f);
        gl.glBegin(GL.GL_TRIANGLES);         
        gl.glVertex3f(0.0f, 0.05f, 0.0f);   // Top            
        gl.glVertex3f(0.f, -.05f, 0.0f); // Bottom Left
        gl.glVertex3f(.55f, 0.0f, 0.0f);  // Bottom Right
        // Finished Drawing The Triangle
        gl.glEnd();
        
        gl.glTranslatef(0.01f, -.09f, 0.0f);
        gl.glColor3f(1.0f, 1.90f,  1.0f);
        gl.glBegin(GL.GL_TRIANGLES);         
        gl.glVertex3f(-0.05f, 0.05f, 0.0f);   // Top            
        gl.glVertex3f(0.05f, .05f, 0.0f); // Bottom Left
        gl.glVertex3f(-0.03f,- 0.50f, 0.0f);  // Bottom Right
        // Finished Drawing The Triangle
        gl.glEnd();
        
        gl.glTranslatef(-0.03f, 0.070f, 0.0f);
        gl.glColor3f(1.0f, 1.90f,  1.0f);
        gl.glBegin(GL.GL_TRIANGLES);         
        gl.glVertex3f(0.0f, 0.05f, 0.0f);   // Top            
        gl.glVertex3f(0.0f, -0.05f, 0.0f); // Bottom Left
        gl.glVertex3f(-0.55f, -0.05f, 0.0f);  // Bottom Right
        // Finished Drawing The Triangle
        gl.glEnd();
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }
    
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

