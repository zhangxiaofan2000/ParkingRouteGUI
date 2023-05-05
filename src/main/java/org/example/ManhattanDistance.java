package org.example;

import org.jgrapht.alg.interfaces.AStarAdmissibleHeuristic;

class ManhattanDistance implements AStarAdmissibleHeuristic<String> {
    @Override
    public double getCostEstimate(String sourceVertex, String targetVertex) {
        String[] sourceParts = sourceVertex.split("-");
        int sourceX = Integer.parseInt(sourceParts[0]);
        int sourceY = Integer.parseInt(sourceParts[1]);

        String[] targetParts = targetVertex.split("-");
        int targetX = Integer.parseInt(targetParts[0]);
        int targetY = Integer.parseInt(targetParts[1]);

        return Math.abs(sourceX - targetX) + Math.abs(sourceY - targetY);
    }
}
