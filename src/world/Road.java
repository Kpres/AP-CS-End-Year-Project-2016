package world;

public class Road extends GamePiece {
	
	public Road() {
		super(6);
	}

	public void update() {
		float x = this.position.x;
		float z = this.position.z;
		
		if (Math.abs(x) % 3 >= 1 && Math.abs(x) % 3 <= 2) {
			float rt3 = (float) Math.sqrt(3);
			ry = 60;
			
			if ((int) (Math.abs(x) + 1) % 2 != 0) {
				ry = -ry;
			}
			if ((int) ((Math.abs(z) / rt3)) % 2 != 0) {
				ry = -ry;
			}
			
			if (z > 0) {
				if (x < 0) {
					ry = -ry;
				}
			} else {
				if (x > 0) {
					ry = -ry;
				}
			}
		} else {
			ry = 0;
		}
	}
}
