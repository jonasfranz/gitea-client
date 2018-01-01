import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BranchesTest : RepositoryTest() {
    private val branchToTest = "master"

    @Test
    fun testListBranches() {
        val branches = runBlocking { repository.listBranches() }
        assertTrue("There must be at least one branch") {
            branches.isNotEmpty()
        }
        assertTrue("There must be a master branch") {
            branches.any { it.name == "master" }
        }
    }

    @Test
    fun testGetBranch() {
        val branch = runBlocking { repository.getBranch(branchToTest) }
        assertEquals(branchToTest, branch.name)
    }
}