class WeightedRoundRobin {

    private String[] servers;
    private int[] weights;

    private int currentIndex = -1;
    private int currentWeight = 0;

    public WeightedRoundRobin(String[] servers, int[] weights) {
        this.servers = servers;
        this.weights = weights;
    }

    public String getNextServer() {

        while (true) {

            currentIndex =
                (currentIndex + 1) % servers.length;

            if (currentIndex == 0) {

                currentWeight -= 1;

                if (currentWeight <= 0) {
                    currentWeight = getMaxWeight();
                }
            }

            if (weights[currentIndex] >= currentWeight) {
                return servers[currentIndex];
            }
        }
    }

    private int getMaxWeight() {

        int max = weights[0];

        for (int weight : weights) {
            if (weight > max) {
                max = weight;
            }
        }

        return max;
    }
}

public class Main {

    public static void main(String[] args) {

        String[] servers = {
            "Server1",
            "Server2",
            "Server3"
        };

        int[] weights = {5, 1, 1};

        WeightedRoundRobin loadBalancer =
            new WeightedRoundRobin(servers, weights);

        for (int i = 0; i < 7; i++) {

            String server =
                loadBalancer.getNextServer();

            System.out.println(
                "Request " + (i + 1) + " -> " + server
            );
        }
    }
}
