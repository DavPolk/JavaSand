public class Gas extends Particle {

    private int maxFlow = 3;
    private int nudgePercentage = 10;
    public Gas(int x, int y) {
        super(x, y);
    }

    @Override
    public void update(Grid g){
        if(!ignoreNextGravity){
            gasUpdate(g, maxFlow, nudgePercentage);
        }
        else{
            ignoreNextGravity = false;
        }
    }

    public void gasUpdate(Grid g, int flow, int nudge){
        if(gasSettleUp(g, nudge, flow))
            return;
        else{
            if(Math.random()*2 >= 1){
                if(gasSettleLeft(g, flow))
                    return;
                else{
                    if(gasSettleRight(g, flow))
                        return;
                }
            }
            else{
                if(gasSettleRight(g, flow))
                    return;
                else{
                    if(gasSettleLeft(g, flow))
                        return;
                }
            }
        }
        fluidJiggle(g, 50);
    }

}
