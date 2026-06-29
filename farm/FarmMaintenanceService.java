public class FarmMaintenanceService {

    public Invoice calculateMaintenanceCost(FarmArea farm, int durationInMonths) {

        System.out.println(
                "Bắt đầu tính toán chi phí bảo trì hệ thống cho khu vực: "
                        + farm.getName());

        MaintenanceStrategy strategy =
                MaintenanceStrategyFactory.getStrategy(
                        farm.getTerrainType());

        return strategy.calculate(farm, durationInMonths);
    }
}