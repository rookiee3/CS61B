public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;	
	}
	public double calcDistance(Planet a){
		return Math.sqrt((a.xxPos-this.xxPos)*(a.xxPos-this.xxPos)+(a.yyPos-this.yyPos)*(a.yyPos-this.yyPos));
	}
	public double calcForceExertedBy(Planet a){
		return ((6.67e-11)*this.mass*a.mass)/(this.calcDistance(a)*this.calcDistance(a));
	}
	public double calcForceExertedByX(Planet a){
		return this.calcForceExertedBy(a)*((-this.xxPos+a.xxPos)/this.calcDistance(a));
	}
	public double calcForceExertedByY(Planet a){
		return this.calcForceExertedBy(a)*((-this.yyPos+a.yyPos)/this.calcDistance(a));
	}
	public double calcNetForceExertedByX(Planet[] allPlanet){
		double netForceX = 0;
		for(int i=0; i<allPlanet.length; i++){
			if(this.equals(allPlanet[i])){
				netForceX += 0;
			}
			else {
				netForceX += this.calcForceExertedByX(allPlanet[i]);
			}
		}
		return netForceX;
	}
	public double calcNetForceExertedByY(Planet[] allPlanet){
		double netForceY = 0;
		for(int i=0; i<allPlanet.length; i++){
			if(this.equals(allPlanet[i])){
				netForceY += 0;
			}
			else {
				netForceY += this.calcForceExertedByY(allPlanet[i]);
			}
		}
		return netForceY;
	}
	public void update(double dt, double fX, double fY){
		double accx=fX/this.mass;
		double accy=fY/this.mass;
		this.xxVel += accx*dt;
		this.yyVel += accy*dt;
		this.xxPos += xxVel*dt;
		this.yyPos += yyVel*dt;
	}
	public void draw(){
		StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
	}
}
