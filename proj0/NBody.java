import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class NBody{
	public static double readRadius(String path){
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			br.readLine();
			String secondLine = br.readLine();
			double radius = Double.parseDouble(secondLine);
			return radius;
		}catch (IOException e){
			e.printStackTrace();
			return 0.0;
		}
	}
	public static Planet[] readPlanets(String path){
		In in = new In(path);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] pla = new Planet[num];
		for(int i = 0; i<num; i++){
			double xp = in.readDouble();
			double yp = in.readDouble();
			double xv = in.readDouble();
			double yv = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			pla[i] = new Planet(xp,yp,xv,yv,m,img);
		}
		return pla;
	}
	public static void main(String args[]){
		double T = new Double(args[0]);
		double dt = new Double(args[1]);
		String filename = args[2];
		double r = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		// set the universe scale
		StdDraw.setXscale(-r, r);
		StdDraw.setYscale(-r, r);
		StdDraw.enableDoubleBuffering();

		double t = 0;
		int num = planets.length;
		while(t <= T){
			double[] xForces = new double[num];
			double[] yForces = new double[num];
			for(int i = 0; i < num; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i = 0; i < num; i++){
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			// draw the backgroud picture
			StdDraw.picture(0, 0, "images/starfield.jpg");

			// draw all the planets
			for (Planet planet : planets) {
				planet.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
			t += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}
	}	
}
