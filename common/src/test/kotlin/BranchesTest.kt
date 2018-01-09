import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BranchesTest : RepositoryTest() {
    private val branchToTest = "master"

    @Test
    fun testListBranches() = runBlocking {
        val branches = repository.listBranches()
        assertTrue("There must be at least one branch") {
            branches.isNotEmpty()
        }
        assertTrue("There must be a master branch") {
            branches.any { it.name == "master" }
        }
    }

    @Test
    fun testGetBranch() = runBlocking {
        val branch = repository.getBranch(branchToTest)
        assertEquals(branchToTest, branch.name)
    }
}