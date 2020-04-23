package ParticleSimulation;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdDraw;

public class CollisionSystem {
    private MinPQ<Event> pq;
    private double t = 0.0;
    private Particle[] particles;

    public CollisionSystem(Particle[] particles) {
        this.particles = particles;
    }

    private void predict(Particle p) {
        if (p == null) return;
        for (int i = 0; i < particles.length; i++) {
            double dt = p.timeToHit(particles[i]);
            pq.insert(new Event(t + dt, p, particles[i]));
        }
        pq.insert(new Event(t + p.timeToHitVerticalWall(), p, null));
        pq.insert(new Event(t + p.timeToHitHorizontalWall(), null, p));
    }

    private void redraw() {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }
        StdDraw.show(50);

        pq.insert(new Event(t+0.5, null, null));
    }

    public void simulate() {
        pq = new MinPQ<Event>();
        for (int i = 0; i < particles.length; i++) predict(particles[i]);
        pq.insert(new Event(0, null, null));

        while (!pq.isEmpty()) {
            Event event = pq.delMin();
            if (!event.isValid()) continue;
            Particle p1 = event.p1;
            Particle p2 = event.p2;

            for (int i = 0; i < particles.length; i++) {
                particles[i].move(event.time - t);
            }
            t = event.time;

            if (p1 != null && p2 != null) p1.bounceOff(p2);
            else if (p1 != null) p1.bounceOffVerticalWall();
            else if (p2 != null) p2.bounceOffHorizontalWall();
            else if (p1 == null && p2 == null) redraw();

            predict(p1);
            predict(p2);
        }
    }

    private class Event implements Comparable<Event> {
        private double time;
        private Particle p1, p2;
        private int countP1, countP2;

        public Event(double t, Particle p1, Particle p2) {
            this.time = t;
            this.p1 = p1;
            this.p2 = p2;
            if (p1 != null) countP1 = p1.count();
            else countP1 = -1;
            if (p2 != null) countP2 = p2.count();
            else countP2 = -1;
        }

        public int compareTo(Event that) {
            if (this.time - that.time > 0) return 1;
            else if (this.time - that.time < 0) return -1;
            return 0;
        }

        public boolean isValid() {
            if (p1 != null && p1.count() != countP1) return false;
            if (p2 != null && p2.count() != countP2) return false;
            return true;
        }
    }

    public static void main(String[] args) {
        int amount = 400;
        Particle[] particles = new Particle[amount];
        for (int i = 0; i < amount; i++) {
            particles[i] = new Particle();
        }
        CollisionSystem cs = new CollisionSystem(particles);
        cs.simulate();
    }
}
